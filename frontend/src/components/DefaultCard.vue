<template>
  <div class="card card-primary">
    <div class="card-header">
      <h3 class="card-title">{{ titulo }}</h3>
    </div>
    <slot />
  </div>
</template>
<script>
export default {
  name: "DefaultCard",
  props: { titulo: String },
};
</script>
<style></style>
