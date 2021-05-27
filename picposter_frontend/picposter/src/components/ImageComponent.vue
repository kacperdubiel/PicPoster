<template>
    <img :src="image" class="img-fluid">
</template>

<script>
import axios from "axios";

export default {
  name: 'image-component',
  props: {
      filename: {
        type: String
      }
  },
  data(){
    return {
      image: ""
    }
  },
  methods:{
    getImg(){
      let config = {
          url: 'http://localhost:8090/upload/' + this.filename,
          method: 'GET',
          responseType: 'blob'
      }
      axios(config)
          .then((response) => {
              let reader = new FileReader();
              reader.readAsDataURL(response.data); 
              reader.onload = () => {
                  this.image = reader.result;
              }
          });
    },

  },
  mounted(){
    this.getImg()
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>


</style>