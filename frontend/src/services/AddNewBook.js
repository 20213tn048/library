import axios from 'axios';

let url = 'http://localhost:8080/books/'

const addNewBook = async (name, author, year,genreId) => {
    try{
        const res = await axios.post(url, {
            name: name,
            author: author,
            publicationYear: year,
            fkGenreId: genreId
        })
        return res.data
    }catch(error){
        throw error
    }
}

export default addNewBook