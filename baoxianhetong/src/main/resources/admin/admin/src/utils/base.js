const base = {
    get() {
        return {
            url : "http://localhost:8080/baoxianhetong/",
            name: "baoxianhetong",
            // 退出到首页链接
            indexUrl: 'http://localhost:8080/baoxianhetong/front/index.html'
        };
    },
    getProjectName(){
        return {
            projectName: "可盈保险合同管理系统"
        } 
    }
}
export default base
