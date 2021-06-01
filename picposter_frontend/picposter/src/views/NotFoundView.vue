<template>
<div>
    <navbar-component />

    <div id="not-found-page">
        <h1>Błąd 404</h1>
        <h3>Strona nie została odnaleziona!</h3>
    </div>
</div>
</template>

<script>
import NavbarComponent from '../components/NavbarComponent.vue'

export default {
  name: 'NotFoundView',
  components: {
    NavbarComponent
  },
}
</script>

<style>
#not-found-page {
    display: flex;
    flex-direction: column;
    justify-content: center;
    align-items: center;
    text-align: center;
    height: 80vh
}
</style>