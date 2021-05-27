<template>
  <div id="add-comment-container">
     <form>
        <input type="text" class="form-control" id="new-comment" placeholder="Dodaj komentarz...">
    </form>
    <button type="submit" class="btn btn-primary">Dodaj</button>
  </div>
</template>

<script>
// import axios from "axios"

export default {
  name: 'add-comment-component',
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

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
#add-comment-container {
    display: flex;
    justify-content: space-between;
    margin-top: 7px;
}

#add-comment-container  > form {
    flex-grow: 4;
    margin-right: 10px;
}

#add-comment-container  > #form-group {
    margin-bottom: 0px;
}

#add-comment-container  > button {
    flex-grow: 1;
    padding: 2px 10px;
    height: 38px;
}
</style>