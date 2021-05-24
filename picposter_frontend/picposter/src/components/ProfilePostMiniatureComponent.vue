<template>
    <div class="miniature-post-photo-container">
        <img :src="image">
    </div>
</template>

<script>
import axios from 'axios'
export default {
    name: 'profile-post-miniature-component',
    data() {
        return {
            image: ""
        }
    },
    props: {
        user: {
            type: Object
        },
        post:{
            type: Object
        }
    },
    methods:{
        getImage(){
            let config = {
                url: 'http://localhost:8090/upload/' + this.post.imagePath,
                method: 'GET',
                responseType: 'blob'
            }
            axios(config).then((response) => {
                let reader = new FileReader();
                reader.readAsDataURL(response.data);
                reader.onload = () => {
                    return this.image = reader.result;
                }
             });
        }
    },
    mounted(){
        this.getImage()
    }
}
</script>
<style scoped>
    .miniature-post-photo-container{
        display: flex;
        width: 30%;
        height: 30%;
        margin-block: 1%;

    }
</style>