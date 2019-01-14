import 'babel-polyfill';
import {createRenderer} from 'vue-server-renderer';
import awaitServer from './utils/awaitServer';
const { renderToString } = createRenderer();

import Vue from 'vue'
import App from './App.vue'

// 生产部署时候需要设置为false
Vue.config.productionTip = true

global.renderServer = (comments) => {
    var results = awaitServer((done) => {
        const vm = new Vue({
            render: h => h(App),
        })
        renderToString(vm, (err, res) => {
            done(err, res);
        });
    });
    if (results.error) {
        throw results.error;
    } else {
        return results.result;
    }
};


