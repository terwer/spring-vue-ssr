import 'babel-polyfill';

import Vue from 'vue'
import App from './App.vue'

// 生产部署时候需要设置为false
Vue.config.productionTip = true

global.renderClient = () => {
    const vm = new Vue({
        render: h => h(App),
    })
    vm.$mount('#app')
};

