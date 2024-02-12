import axios from 'axios'

let url = 'http://localhost:8080/books/'

const getBooks = async () =>{
    try{
        const res = await axios.get(url)
        return res.data;
    }catch(error){
        throw error;
    }
}

export default getBooks