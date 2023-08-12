import axios from 'axios'
import Cookies from 'js-cookie'
import { apiAuth } from './auth';

const api = axios.create({
    baseURL: 'http://localhost:8000',
    timeout: 5000,
    headers: {
        'Content-Type': 'application/json',
    }
})

export const apiCalls = {
    api,

}