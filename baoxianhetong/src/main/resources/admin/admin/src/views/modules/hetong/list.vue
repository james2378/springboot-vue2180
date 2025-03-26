<template>
    <div class="main-content">

        <!-- 条件查询 -->
        <div v-if="showFlag">
            <el-form :inline="true" :model="searchForm" class="form-content">
                <el-row :gutter="20" class="slt" :style="{justifyContent:contents.searchBoxPosition=='1'?'flex-start':contents.searchBoxPosition=='2'?'center':'flex-end'}">
                                 
                     <el-form-item :label="contents.inputTitle == 1 ? '合同唯一编号' : ''">
                         <el-input prefix-icon="el-icon-search" v-model="searchForm.hetongUuidNumber" placeholder="合同唯一编号" clearable></el-input>
                     </el-form-item>
         
                     <el-form-item :label="contents.inputTitle == 1 ? '保险合同类型' : ''">
                        <el-select v-model="searchForm.hetongTypes" placeholder="请选择保险合同类型">
                            <el-option label="=-请选择-=" value=""></el-option>
                            <el-option
                               v-for="(item,index) in hetongTypesSelectSearch"
                               v-bind:key="index"
                               :label="item.indexName"
                               :value="item.codeIndex">
                            <!--lable是要展示的名称-->
                            <!--value是值-->
                            </el-option>
                        </el-select>
                     </el-form-item>

         
                     <el-form-item :label="contents.inputTitle == 1 ? '长短类型' : ''">
                        <el-select v-model="searchForm.changduanTypes" placeholder="请选择长短类型">
                            <el-option label="=-请选择-=" value=""></el-option>
                            <el-option
                               v-for="(item,index) in changduanTypesSelectSearch"
                               v-bind:key="index"
                               :label="item.indexName"
                               :value="item.codeIndex">
                            <!--lable是要展示的名称-->
                            <!--value是值-->
                            </el-option>
                        </el-select>
                     </el-form-item>

                           <!--
                    <el-form-item :label="contents.inputTitle == 1 ? '开始执行日期' : ''">
                        <el-date-picker
                                value-format="yyyy-MM-dd"
                                v-model="searchForm.zhixingTimeStartSearch"
                                type="date"
                                placeholder="开始时间">
                        </el-date-picker>
                    </el-form-item>
                    <el-form-item :label="contents.inputTitle == 1 ? '' : ''">
                        =》
                    </el-form-item>
                    <el-form-item :label="contents.inputTitle == 1 ? '结束执行日期' : ''">
                        <el-date-picker
                                value-format="yyyy-MM-dd"
                                v-model="searchForm.zhixingTimeEndSearch"
                                type="date"
                                placeholder="结束时间">
                        </el-date-picker>
                    </el-form-item>
         
                    <el-form-item :label="contents.inputTitle == 1 ? '开始到期日期' : ''">
                        <el-date-picker
                                value-format="yyyy-MM-dd"
                                v-model="searchForm.daoqiTimeStartSearch"
                                type="date"
                                placeholder="开始时间">
                        </el-date-picker>
                    </el-form-item>
                    <el-form-item :label="contents.inputTitle == 1 ? '' : ''">
                        =》
                    </el-form-item>
                    <el-form-item :label="contents.inputTitle == 1 ? '结束到期日期' : ''">
                        <el-date-picker
                                value-format="yyyy-MM-dd"
                                v-model="searchForm.daoqiTimeEndSearch"
                                type="date"
                                placeholder="结束时间">
                        </el-date-picker>
                    </el-form-item>
                    -->
                
                                                             
                    <el-form-item :label="contents.inputTitle == 1 ? '客户姓名' : ''">
                        <el-input prefix-icon="el-icon-search" v-model="searchForm.kehuName" placeholder="客户姓名" clearable></el-input>
                    </el-form-item>
                                                                                                                                                                 
                    <el-form-item :label="contents.inputTitle == 1 ? '用户姓名' : ''">
                        <el-input prefix-icon="el-icon-search" v-model="searchForm.yonghuName" placeholder="用户姓名" clearable></el-input>
                    </el-form-item>
                                                                                

                    <el-form-item>
                        <el-button type="success" @click="search()">查询<i class="el-icon-search el-icon--right"/></el-button>
                    </el-form-item>
                </el-row>
                <el-row class="ad" :style="{justifyContent:contents.btnAdAllBoxPosition=='1'?'flex-start':contents.btnAdAllBoxPosition=='2'?'center':'flex-end'}">
                    <el-form-item>
                        <el-button
                                v-if="isAuth('hetong','新增')"
                                type="success"
                                icon="el-icon-plus"
                                @click="addOrUpdateHandler()"
                        >新增</el-button>
                        &nbsp;
                        <el-button
                                v-if="isAuth('hetong','删除')"
                                :disabled="dataListSelections.length <= 0"
                                type="danger"
                                icon="el-icon-delete"
                                @click="deleteHandler()"
                        >删除</el-button>
                        &nbsp;
                        <el-button
                                v-if="isAuth('hetong','报表')"
                                type="success"
                                icon="el-icon-pie-chart"
                                @click="chartDialog1()"
                        >保险合同类型报表</el-button>
                        &nbsp;
                        <el-button
                                v-if="isAuth('hetong','报表')"
                                type="success"
                                icon="el-icon-pie-chart"
                                @click="chartDialog2()"
                        >长短期类型报表</el-button>
                        &nbsp;
                        <a style="text-decoration:none" class="el-button el-button--success"
                           v-if="isAuth('hetong','导入导出')"
                           icon="el-icon-download"
                           href="http://localhost:8080/baoxianhetong/upload/hetongMuBan.xls"
                        >批量导入合同数据模板</a>
                        &nbsp;
                        <el-upload
                                v-if="isAuth('hetong','导入导出')"
                                style="display: inline-block"
                                action="baoxianhetong/file/upload"
                                :on-success="hetongUploadSuccess"
                                :on-error="hetongUploadError"
                                :show-file-list = false>
                            <el-button
                                    v-if="isAuth('hetong','导入导出')"
                                    type="success"
                                    icon="el-icon-upload2"
                            >批量导入合同数据</el-button>
                        </el-upload>
                        &nbsp;
                        <!-- 导出excel -->
                        <download-excel v-if="isAuth('hetong','导入导出')" style="display: inline-block" class = "export-excel-wrapper" :data = "dataList" :fields = "json_fields" name = "hetong.xls">
                            <!-- 导出excel -->
                            <el-button
                                    type="success"
                                    icon="el-icon-download"
                            >导出</el-button>
                        </download-excel>
                        &nbsp;
                    </el-form-item>
                </el-row>
            </el-form>
            <div class="table-content">
                <el-table class="tables" :size="contents.tableSize" :show-header="contents.tableShowHeader"
                          :header-row-style="headerRowStyle" :header-cell-style="headerCellStyle"
                          :border="contents.tableBorder"
                          :fit="contents.tableFit"
                          :stripe="contents.tableStripe"
                          :row-style="rowStyle"
                          :cell-style="cellStyle"
                          :style="{width: '100%',fontSize:contents.tableContentFontSize,color:contents.tableContentFontColor}"
                          v-if="isAuth('hetong','查看')"
                          :data="dataList"
                          v-loading="dataListLoading"
                          @selection-change="selectionChangeHandler">
                    <el-table-column  v-if="contents.tableSelection"
                                      type="selection"
                                      header-align="center"
                                      align="center"
                                      width="50">
                    </el-table-column>
                    <el-table-column label="索引" v-if="contents.tableIndex" type="index" width="50" />
                    <el-table-column  :sortable="contents.tableSortable" :align="contents.tableAlign"
                                      prop="kehuName"
                                      header-align="center"
                                      label="客户姓名">
                        <template slot-scope="scope">
                            {{scope.row.kehuName}}
                        </template>
                    </el-table-column>
                    
                    <el-table-column  :sortable="contents.tableSortable" :align="contents.tableAlign"
                                      prop="yonghuName"
                                      header-align="center"
                                      label="用户姓名">
                        <template slot-scope="scope">
                            {{scope.row.yonghuName}}
                        </template>
                    </el-table-column>

                    <el-table-column  :sortable="contents.tableSortable" :align="contents.tableAlign"
                                   prop="hetongUuidNumber"
                                   header-align="center"
                                   label="合同唯一编号">
                        <template slot-scope="scope">
                            {{scope.row.hetongUuidNumber}}
                        </template>
                    </el-table-column>
                    <el-table-column  :sortable="contents.tableSortable" :align="contents.tableAlign"
                                      prop="hetongTypes"
                                      header-align="center"
                                      label="保险合同类型">
                        <template slot-scope="scope">
                            {{scope.row.hetongValue}}
                        </template>
                    </el-table-column>
                    <el-table-column  :sortable="contents.tableSortable" :align="contents.tableAlign"
                                      prop="changduanTypes"
                                      header-align="center"
                                      label="长短类型">
                        <template slot-scope="scope">
                            {{scope.row.changduanValue}}
                        </template>
                    </el-table-column>
                    <el-table-column :sortable="contents.tableSortable" :align="contents.tableAlign" prop="hetongFile"
                                     header-align="center"
                                     width="200"
                                     label="合同文件">
                        <template slot-scope="scope">
                            <div v-if="scope.row.hetongFile">
                                <a type="text" style="text-decoration:none" class="el-button" :href="scope.row.hetongFile"  >下载</a>
                            </div>
                            <div v-else>无文件</div>
                        </template>
                    </el-table-column>

                    <el-table-column  :sortable="contents.tableSortable" :align="contents.tableAlign"
                                   prop="baofeiDouble"
                                   header-align="center"
                                   label="保费">
                        <template slot-scope="scope">
                            {{scope.row.baofeiDouble}}
                        </template>
                    </el-table-column>

                    <el-table-column  :sortable="contents.tableSortable" :align="contents.tableAlign"
                                   prop="baoeDouble"
                                   header-align="center"
                                   label="保额">
                        <template slot-scope="scope">
                            {{scope.row.baoeDouble}}
                        </template>
                    </el-table-column>

                    <el-table-column  :sortable="contents.tableSortable" :align="contents.tableAlign"
                                   prop="shouyirenName"
                                   header-align="center"
                                   label="受益人姓名">
                        <template slot-scope="scope">
                            {{scope.row.shouyirenName}}
                        </template>
                    </el-table-column>

                    <el-table-column  :sortable="contents.tableSortable" :align="contents.tableAlign"
                                   prop="shouyirenPhone"
                                   header-align="center"
                                   label="受益人手机号">
                        <template slot-scope="scope">
                            {{scope.row.shouyirenPhone}}
                        </template>
                    </el-table-column>

                    <el-table-column  :sortable="contents.tableSortable" :align="contents.tableAlign"
                                   prop="zhixingTime"
                                   header-align="center"
                                   label="执行日期">
                        <template slot-scope="scope">
                            {{scope.row.zhixingTime}}
                        </template>
                    </el-table-column>
                    <el-table-column  :sortable="contents.tableSortable" :align="contents.tableAlign"
                                   prop="daoqiTime"
                                   header-align="center"
                                   label="到期日期">
                        <template slot-scope="scope">
                            {{scope.row.daoqiTime}}
                        </template>
                    </el-table-column>

                    <el-table-column  :sortable="contents.tableSortable" :align="contents.tableAlign"
                                   prop="daoqiTime"
                                   header-align="center"
                                   label="是否提醒">
                        <template slot-scope="scope">
                            <!--{{scope.row.daoqiTime}}&lt;%&ndash;&ndash;%&gt;-->
                            <span                    v-if="calculation(scope.row.daoqiTime) == 1">已到期</span>
                            <span style="color: red" v-else-if="calculation(scope.row.daoqiTime) == 2 " >一月内到期</span>
                            <span v-else>未到期</span>
                        </template>
                    </el-table-column>

                    <el-table-column  :sortable="contents.tableSortable" :align="contents.tableAlign"
                                   prop="insertTime"
                                   header-align="center"
                                   label="添加时间">
                        <template slot-scope="scope">
                            {{scope.row.insertTime}}
                        </template>
                    </el-table-column>

                    <el-table-column width="300" :align="contents.tableAlign"
                                     header-align="center"
                                     label="操作">
                        <template slot-scope="scope">
                            <el-button v-if="isAuth('hetong','查看')" type="success" icon="el-icon-tickets" size="mini" @click="addOrUpdateHandler(scope.row.id,'info')">详情</el-button>
                            <el-button v-if="isAuth('hetong','修改')" type="primary" icon="el-icon-edit" size="mini" @click="addOrUpdateHandler(scope.row.id)">修改</el-button>
                            <el-button v-if="isAuth('hetong','删除')" type="danger" icon="el-icon-delete" size="mini" @click="deleteHandler(scope.row.id)">删除</el-button>

                        </template>
                    </el-table-column>
                </el-table>
                <el-pagination
                        clsss="pages"
                        :layout="layouts"
                        @size-change="sizeChangeHandle"
                        @current-change="currentChangeHandle"
                        :current-page="pageIndex"
                        :page-sizes="[10, 20, 50, 100]"
                        :page-size="Number(contents.pageEachNum)"
                        :total="totalPage"
                        :small="contents.pageStyle"
                        class="pagination-content"
                        :background="contents.pageBtnBG"
                        :style="{textAlign:contents.pagePosition==1?'left':contents.pagePosition==2?'center':'right'}"
                ></el-pagination>
            </div>
        </div>
        <!-- 添加/修改页面  将父组件的search方法传递给子组件-->
        <add-or-update v-if="addOrUpdateFlag" :parent="this" ref="addOrUpdate"></add-or-update>
    
        <el-dialog title="统计报表" :visible.sync="chartVisiable" width="800">
            <div id="statistic" style="width:100%;height:600px;"></div>
            <span slot="footer" class="dialog-footer">
				<el-button @click="chartVisiable = false">返回</el-button>
			</span>
        </el-dialog>

    </div>
</template>
<script>
    import AddOrUpdate from "./add-or-update";
    import styleJs from "../../../utils/style.js";
    export default {
        data() {
        return {
            searchForm: {
                key: ""
            },
            sessionTable : "",//登录账户所在表名
            role : "",//权限
            userId:"",//当前登录人的id
    //级联表下拉框搜索条件
    //当前表下拉框搜索条件
              hetongTypesSelectSearch : [],
              changduanTypesSelectSearch : [],
            form:{
                id : null,
                yonghuId : null,
                kehuId : null,
                hetongUuidNumber : null,
                hetongTypes : null,
                changduanTypes : null,
                hetongFile : null,
                baofeiDouble : null,
                baoeDouble : null,
                shouyirenName : null,
                shouyirenPhone : null,
                shouyirenIdNumber : null,
                shouyirenAddress : null,
                hetongContent : null,
                zhixingTime : null,
                daoqiTime : null,
                insertTime : null,
                createTime : null,
            },
            dataList: [],
            pageIndex: 1,
            pageSize: 10,
            totalPage: 0,
            dataListLoading: false,
            dataListSelections: [],
            showFlag: true,
            sfshVisiable: false,
            shForm: {},
            chartVisiable: false,
            addOrUpdateFlag:false,
            contents:null,
            layouts: '',

            echartsDate: new Date(),//echarts的时间查询字段

            //导出excel
            json_fields: {
                //级联表字段
                     '客户姓名': 'kehuName',
                     '客户手机号': 'kehuPhone',
                     '客户身份证号': 'kehuIdNumber',
                     '客户照片': 'kehuPhoto',
                     '性别': 'sexTypes',
                     '用户姓名': 'yonghuName',
                     '用户手机号': 'yonghuPhone',
                     '用户身份证号': 'yonghuIdNumber',
                     '用户头像': 'yonghuPhoto',
                //本表字段
                     '合同唯一编号': "hetongUuidNumber",
                     '保险合同类型': "hetongTypes",
                     '长短类型': "changduanTypes",
                     '合同文件': "hetongFile",
                     '保费': "baofeiDouble",
                     '保额': "baoeDouble",
                     '受益人姓名': "shouyirenName",
                     '受益人手机号': "shouyirenPhone",
                     '受益人身份证号': "shouyirenIdNumber",
                     '受益人地址': "shouyirenAddress",
                     '执行日期': "zhixingTime",
                     '到期日期': "daoqiTime",
                     '添加时间': "insertTime",
            },

            };
        },
        created() {
            this.contents = styleJs.listStyle();
            this.init();
            this.getDataList();
            this.contentStyleChange()
        },
        mounted() {
            //获取当前登录用户的信息
            this.sessionTable = this.$storage.get("sessionTable");
            this.role = this.$storage.get("role");
            this.userId = this.$storage.get("userId");

        },
        filters: {
            htmlfilter: function (val) {
                return val.replace(/<[^>]*>/g).replace(/undefined/g,'');
            }
        },
        components: {
            AddOrUpdate,
        },
        computed: {
        },
        methods: {
            chartDialog1() {
                let _this = this;
                this.chartVisiable = true;
                this.$nextTick(()=>{
                    var statistic = this.$echarts.init(document.getElementById("statistic"),'macarons');
                    let params = {
                        tableName: "hetong",
                        groupColumn: "hetong_types",
                        yonghuId: this.userId,
                    }
                    this.$http({
                        url: "newSelectGroupCount",
                        method: "get",
                        params: params
                    }).then(({data}) => {
                        if (data && data.code === 0) {
                            let res = data.data;
                            let xAxis = [];
                            let yAxis = [];
                            let pArray = []
                            for(let i=0;i<res.length;i++){
                                xAxis.push(res[i].name);
                                yAxis.push(res[i].value);
                                pArray.push({
                                    value: res[i].value,
                                    name: res[i].name
                                })
                                var option = {};
                                option = {
                                    title: {
                                        text: '保险合同类型统计',
                                        left: 'center'
                                    },
                                    tooltip: {
                                        trigger: 'item',
                                        formatter: '{b} : {c} ({d}%)'
                                    },
                                    series: [
                                        {
                                            type: 'pie',
                                            radius: '55%',
                                            center: ['50%', '60%'],
                                            data: pArray,
                                            emphasis: {
                                                itemStyle: {
                                                    shadowBlur: 10,
                                                    shadowOffsetX: 0,
                                                    shadowColor: 'rgba(0, 0, 0, 0.5)'
                                                }
                                            }
                                        }
                                    ]
                                };
                                statistic.setOption(option);
                                window.onresize = function() {
                                    statistic.resize();
                                };
                            }
                        }
                    });
                })
            },
            chartDialog2() {
                let _this = this;
                this.chartVisiable = true;
                this.$nextTick(()=>{
                    var statistic = this.$echarts.init(document.getElementById("statistic"),'macarons');
                    let params = {
                        tableName: "hetong",
                        groupColumn: "changduan_types",
                        yonghuId: _this.userId,
                    }
                    this.$http({
                        url: "newSelectGroupCount",
                        method: "get",
                        params: params
                    }).then(({data}) => {
                        if (data && data.code === 0) {
                            let res = data.data;
                            let xAxis = [];
                            let yAxis = [];
                            let pArray = []
                            for(let i=0;i<res.length;i++){
                                xAxis.push(res[i].name);
                                yAxis.push(res[i].value);
                                pArray.push({
                                    value: res[i].value,
                                    name: res[i].name
                                })
                                var option = {};
                                option = {
                                    title: {
                                        text: '长短期统计',
                                        left: 'center'
                                    },
                                    tooltip: {
                                        trigger: 'item',
                                        formatter: '{b} : {c} ({d}%)'
                                    },
                                    series: [
                                        {
                                            type: 'pie',
                                            radius: '55%',
                                            center: ['50%', '60%'],
                                            data: pArray,
                                            emphasis: {
                                                itemStyle: {
                                                    shadowBlur: 10,
                                                    shadowOffsetX: 0,
                                                    shadowColor: 'rgba(0, 0, 0, 0.5)'
                                                }
                                            }
                                        }
                                    ]
                                };
                                // 使用刚指定的配置项和数据显示图表。
                                statistic.setOption(option);
                                //根据窗口的大小变动图表
                                window.onresize = function() {
                                    statistic.resize();
                                };
                            }
                        }
                    });
                    // xcolumn ycolumn
                })
            },
            calculation(daoqiString) {
                
                let daoqiDate = new Date(daoqiString);
                let thisDate = new Date();
                if(daoqiDate.getTime() < thisDate.getTime()){
                    return 1;
                }else if(new Date(daoqiDate.setMonth(daoqiDate.getMonth()-1)).getTime() < thisDate.getTime()){
                    return 2;
                }else{
                    return 3;
                }

                // new Date(scope.row.daoqiTime).setMonth(new Date(scope.row.daoqiTime)-1).getTime()< new Date().getTime()"
            },
            contentStyleChange() {
                this.contentSearchStyleChange()
                this.contentBtnAdAllStyleChange()
                this.contentSearchBtnStyleChange()
                this.contentTableBtnStyleChange()
                this.contentPageStyleChange()
            },
            contentSearchStyleChange() {
                this.$nextTick(() => {
                    document.querySelectorAll('.form-content .slt .el-input__inner').forEach(el => {
                        let textAlign = 'left'
                        if(this.contents.inputFontPosition == 2)
                            textAlign = 'center'
                            if (this.contents.inputFontPosition == 3) textAlign = 'right'
                                el.style.textAlign = textAlign
                            el.style.height = this.contents.inputHeight
                            el.style.lineHeight = this.contents.inputHeight
                            el.style.color = this.contents.inputFontColor
                            el.style.fontSize = this.contents.inputFontSize
                            el.style.borderWidth = this.contents.inputBorderWidth
                            el.style.borderStyle = this.contents.inputBorderStyle
                            el.style.borderColor = this.contents.inputBorderColor
                            el.style.borderRadius = this.contents.inputBorderRadius
                            el.style.backgroundColor = this.contents.inputBgColor
                    })
                    if (this.contents.inputTitle) {
                        document.querySelectorAll('.form-content .slt .el-form-item__label').forEach(el => {
                            el.style.color = this.contents.inputTitleColor
                            el.style.fontSize = this.contents.inputTitleSize
                            el.style.lineHeight = this.contents.inputHeight
                        })
                    }
                    setTimeout(() => {
                        document.querySelectorAll('.form-content .slt .el-input__prefix').forEach(el => {
                            el.style.color = this.contents.inputIconColor
                            el.style.lineHeight = this.contents.inputHeight
                        })
                        document.querySelectorAll('.form-content .slt .el-input__suffix').forEach(el => {
                            el.style.color = this.contents.inputIconColor
                            el.style.lineHeight = this.contents.inputHeight
                        })
                        document.querySelectorAll('.form-content .slt .el-input__icon').forEach(el => {
                            el.style.lineHeight = this.contents.inputHeight
                        })
                    }, 10 )
                })
            },
            // 搜索按钮
            contentSearchBtnStyleChange() {
                this.$nextTick(() => {
                    document.querySelectorAll('.form-content .slt .el-button--success').forEach(el => {
                        el.style.height = this.contents.searchBtnHeight
                        el.style.color = this.contents.searchBtnFontColor
                        el.style.fontSize = this.contents.searchBtnFontSize
                        el.style.borderWidth = this.contents.searchBtnBorderWidth
                        el.style.borderStyle = this.contents.searchBtnBorderStyle
                        el.style.borderColor = this.contents.searchBtnBorderColor
                        el.style.borderRadius = this.contents.searchBtnBorderRadius
                        el.style.backgroundColor = this.contents.searchBtnBgColor
                    })
                })
            },
            // 新增、批量删除
            contentBtnAdAllStyleChange() {
                this.$nextTick(() => {
                    document.querySelectorAll('.form-content .ad .el-button--success').forEach(el => {
                        el.style.height = this.contents.btnAdAllHeight
                        el.style.color = this.contents.btnAdAllAddFontColor
                        el.style.fontSize = this.contents.btnAdAllFontSize
                        el.style.borderWidth = this.contents.btnAdAllBorderWidth
                        el.style.borderStyle = this.contents.btnAdAllBorderStyle
                        el.style.borderColor = this.contents.btnAdAllBorderColor
                        el.style.borderRadius = this.contents.btnAdAllBorderRadius
                        el.style.backgroundColor = this.contents.btnAdAllAddBgColor
                    })
                    document.querySelectorAll('.form-content .ad .el-button--danger').forEach(el => {
                        el.style.height = this.contents.btnAdAllHeight
                        el.style.color = this.contents.btnAdAllDelFontColor
                        el.style.fontSize = this.contents.btnAdAllFontSize
                        el.style.borderWidth = this.contents.btnAdAllBorderWidth
                        el.style.borderStyle = this.contents.btnAdAllBorderStyle
                        el.style.borderColor = this.contents.btnAdAllBorderColor
                        el.style.borderRadius = this.contents.btnAdAllBorderRadius
                        el.style.backgroundColor = this.contents.btnAdAllDelBgColor
                    })
                    document.querySelectorAll('.form-content .ad .el-button--warning').forEach(el => {
                        el.style.height = this.contents.btnAdAllHeight
                        el.style.color = this.contents.btnAdAllWarnFontColor
                        el.style.fontSize = this.contents.btnAdAllFontSize
                        el.style.borderWidth = this.contents.btnAdAllBorderWidth
                        el.style.borderStyle = this.contents.btnAdAllBorderStyle
                        el.style.borderColor = this.contents.btnAdAllBorderColor
                        el.style.borderRadius = this.contents.btnAdAllBorderRadius
                        el.style.backgroundColor = this.contents.btnAdAllWarnBgColor
                    })
                })
            },
            // 表格
            rowStyle({row, rowIndex}) {
                if (rowIndex % 2 == 1) {
                    if (this.contents.tableStripe) {
                        return {color: this.contents.tableStripeFontColor}
                    }
                } else {
                    return ''
                }
            },
            cellStyle({row, rowIndex}) {
                if (rowIndex % 2 == 1) {
                    if (this.contents.tableStripe) {
                        return {backgroundColor: this.contents.tableStripeBgColor}
                    }
                } else {
                    return ''
                }
            },
            headerRowStyle({row, rowIndex}) {
                return {color: this.contents.tableHeaderFontColor}
            },
            headerCellStyle({row, rowIndex}) {
                return {backgroundColor: this.contents.tableHeaderBgColor}
            },
            // 表格按钮
            contentTableBtnStyleChange() {
                // this.$nextTick(()=>{
                //   setTimeout(()=>{
                //     document.querySelectorAll('.table-content .tables .el-table__body .el-button--success').forEach(el=>{
                //       el.style.height = this.contents.tableBtnHeight
                //       el.style.color = this.contents.tableBtnDetailFontColor
                //       el.style.fontSize = this.contents.tableBtnFontSize
                //       el.style.borderWidth = this.contents.tableBtnBorderWidth
                //       el.style.borderStyle = this.contents.tableBtnBorderStyle
                //       el.style.borderColor = this.contents.tableBtnBorderColor
                //       el.style.borderRadius = this.contents.tableBtnBorderRadius
                //       el.style.backgroundColor = this.contents.tableBtnDetailBgColor
                //     })
                //     document.querySelectorAll('.table-content .tables .el-table__body .el-button--primary').forEach(el=>{
                //       el.style.height = this.contents.tableBtnHeight
                //       el.style.color = this.contents.tableBtnEditFontColor
                //       el.style.fontSize = this.contents.tableBtnFontSize
                //       el.style.borderWidth = this.contents.tableBtnBorderWidth
                //       el.style.borderStyle = this.contents.tableBtnBorderStyle
                //       el.style.borderColor = this.contents.tableBtnBorderColor
                //       el.style.borderRadius = this.contents.tableBtnBorderRadius
                //       el.style.backgroundColor = this.contents.tableBtnEditBgColor
                //     })
                //     document.querySelectorAll('.table-content .tables .el-table__body .el-button--danger').forEach(el=>{
                //       el.style.height = this.contents.tableBtnHeight
                //       el.style.color = this.contents.tableBtnDelFontColor
                //       el.style.fontSize = this.contents.tableBtnFontSize
                //       el.style.borderWidth = this.contents.tableBtnBorderWidth
                //       el.style.borderStyle = this.contents.tableBtnBorderStyle
                //       el.style.borderColor = this.contents.tableBtnBorderColor
                //       el.style.borderRadius = this.contents.tableBtnBorderRadius
                //       el.style.backgroundColor = this.contents.tableBtnDelBgColor
                //     })

                //   }, 50)
                // })
            },
            // 分页
            contentPageStyleChange() {
                let arr = []
                if (this.contents.pageTotal) arr.push('total')
                if (this.contents.pageSizes) arr.push('sizes')
                if (this.contents.pagePrevNext) {
                    arr.push('prev')
                    if (this.contents.pagePager) arr.push('pager')
                    arr.push('next')
                }
                if (this.contents.pageJumper) arr.push('jumper')
                this.layouts = arr.join()
                this.contents.pageEachNum = 10
            },

            init() {
            },
            search() {
                this.pageIndex = 1;
                this.getDataList();
            },
            // 获取数据列表
            getDataList() {
                this.dataListLoading = true;
                let params = {
                    page: this.pageIndex,
                    limit: this.pageSize,
                    sort: 'id',
                }

                                                             
                if (this.searchForm.kehuName!= '' && this.searchForm.kehuName!= undefined) {
                    params['kehuName'] = '%' + this.searchForm.kehuName + '%'
                }
                                                                                                                                                                 
                if (this.searchForm.yonghuName!= '' && this.searchForm.yonghuName!= undefined) {
                    params['yonghuName'] = '%' + this.searchForm.yonghuName + '%'
                }
                                                                                                                 
                if (this.searchForm.hetongUuidNumber!= '' && this.searchForm.hetongUuidNumber!= undefined) {
                    params['hetongUuidNumber'] = '%' + this.searchForm.hetongUuidNumber + '%'
                }
         
                if (this.searchForm.hetongTypes!= '' && this.searchForm.hetongTypes!= undefined) {
                    params['hetongTypes'] = this.searchForm.hetongTypes
                }
         
                if (this.searchForm.changduanTypes!= '' && this.searchForm.changduanTypes!= undefined) {
                    params['changduanTypes'] = this.searchForm.changduanTypes
                }
                                                                         

                if (this.searchForm.zhixingTimeStartSearch != '' && this.searchForm.zhixingTimeStartSearch != undefined) {
                    params['zhixingTimeStart'] = this.searchForm.zhixingTimeStartSearch
                }
                if (this.searchForm.zhixingTimeEndSearch != '' && this.searchForm.zhixingTimeEndSearch != undefined) {
                    params['zhixingTimeEnd'] = this.searchForm.zhixingTimeEndSearch
                }
         

                if (this.searchForm.daoqiTimeStartSearch != '' && this.searchForm.daoqiTimeStartSearch != undefined) {
                    params['daoqiTimeStart'] = this.searchForm.daoqiTimeStartSearch
                }
                if (this.searchForm.daoqiTimeEndSearch != '' && this.searchForm.daoqiTimeEndSearch != undefined) {
                    params['daoqiTimeEnd'] = this.searchForm.daoqiTimeEndSearch
                }
                
                params['hetongDelete'] = 1// 逻辑删除字段 1 未删除 2 删除


                this.$http({
                    url: "hetong/page",
                    method: "get",
                    params: params
                }).then(({data}) => {
                    if(data && data.code === 0){
                        this.dataList = data.data.list;
                        this.totalPage = data.data.total;
                    }else{
                        this.dataList = [];
                        this.totalPage = 0;
                    }
                    this.dataListLoading = false;
                });

                //查询级联表搜索条件所有列表
                //查询当前表搜索条件所有列表
                //填充下拉框选项
                this.$http({
                    url: "dictionary/page?dicCode=hetong_types&page=1&limit=100",
                    method: "get",
                }).then(({data}) => {
                    if(data && data.code === 0){
                        this.hetongTypesSelectSearch = data.data.list;
                    }
                });
                //填充下拉框选项
                this.$http({
                    url: "dictionary/page?dicCode=changduan_types&page=1&limit=100",
                    method: "get",
                }).then(({data}) => {
                    if(data && data.code === 0){
                        this.changduanTypesSelectSearch = data.data.list;
                    }
                });
            },
            //每页数
            sizeChangeHandle(val) {
                this.pageSize = val;
                this.pageIndex = 1;
                this.getDataList();
            },
            // 当前页
            currentChangeHandle(val) {
                this.pageIndex = val;
                this.getDataList();
            },
            // 多选
            selectionChangeHandler(val) {
                this.dataListSelections = val;
            },
            // 添加/修改
            addOrUpdateHandler(id, type) {
                this.showFlag = false;
                this.addOrUpdateFlag = true;
                this.crossAddOrUpdateFlag = false;
                if (type != 'info') {
                    type = 'else';
                }
                this.$nextTick(() => {
                    this.$refs.addOrUpdate.init(id, type);
                });
            },
            // 下载
            download(file) {
                window.open(" ${file} ")
            },
            // 删除
            deleteHandler(id) {
                var ids = id ? [Number(id)] : this.dataListSelections.map(item => {
                    return Number(item.id);
                });

                this.$confirm(`确定进行[${id ? "删除" : "批量删除"}]操作?`, "提示", {
                    confirmButtonText: "确定",
                    cancelButtonText: "取消",
                    type: "warning"
                }).then(() => {
                    this.$http({
                        url: "hetong/delete",
                        method: "post",
                        data: ids
                    }).then(({data}) => {
                        if(data && data.code === 0){
                            this.$message({
                                message: "操作成功",
                                type: "success",
                                duration: 1500,
                                onClose: () => {
                                    this.search();
                                }
                            });
                        }else{
                            this.$message.error(data.msg);
                        }
                    });
                });
            },
            // 导入功能上传文件成功后调用导入方法
            hetongUploadSuccess(data){
                let _this = this;
                _this.$http({
                    url: "hetong/batchInsert?fileName=" + data.file,
                    method: "get"
                }).then(({data}) => {
                    if(data && data.code === 0){
                        _this.$message({
                            message: "导入合同数据成功",
                            type: "success",
                            duration: 1500,
                            onClose: () => {
                                _this.search();
                            }
                        });
                    }else{
                        _this.$message.error(data.msg);
                    }
                });

            },
            // 导入功能上传文件失败后调用导入方法
            hetongUploadError(data){
                this.$message.error('上传失败');
            },
        }
    };
</script>
<style lang="scss" scoped>
.slt {
    margin: 0 !important;
    display: flex;
  }

  .ad {
    margin: 0 !important;
    display: flex;
  }

  .pages {
    & /deep/ el-pagination__sizes{
      & /deep/ el-input__inner {
        height: 22px;
        line-height: 22px;
      }
    }
  }


  .el-button+.el-button {
    margin:0;
  }

  .tables {
	& /deep/ .el-button--success {
		height: 36px;
		color: rgba(40, 167, 69, 1);
		font-size: 10px;
		border-width: 0px;
		border-style: solid;
		border-color: #DCDFE6;
		border-radius: 0px;
		background-color: rgba(255, 255, 255, 1);
	}

	& /deep/ .el-button--primary {
		height: 36px;
		color: rgba(255, 193, 7, 1);
		font-size: 10px;
		border-width: 0px;
		border-style: solid;
		border-color: #DCDFE6;
		border-radius: 0px;
		background-color: #fff;
	}

	& /deep/ .el-button--danger {
		height: 36px;
		color: rgba(220, 53, 69, 1);
		font-size: 10px;
		border-width: 0px;
		border-style: solid;
		border-color: #DCDFE6;
		border-radius: 0px;
		background-color: #fff;
	}

    & /deep/ .el-button {
      margin: 4px;
    }
  }</style>


