<template>
  <div id="grid-img">
    <router-link :to="{ name: 'PostView', params: { postId: post.id } }">
      <image-component :filename="post.imagePath"/>
    </router-link>
  </div>
</template>

<script>
import ImageComponent from './ImageComponent.vue'
// import LikesCounterComponent from './LikesCounterComponent.vue'

export default {
  name: 'post-component',
  components: {
    ImageComponent,
    // LikesCounterComponent
  },
  props: {
      post: {
          type: Object
      }
  },
  data(){
    return {
      
    }
  },
  methods:{
    
  },
  mounted(){
  }
}
</script>

<style scoped>
  
</style>