(() => {
  const tabs = document.querySelectorAll('#flowTabs button[data-bs-toggle="pill"]');
  tabs.forEach(btn => btn.addEventListener('shown.bs.tab', () => window.scrollTo({top:0, behavior:'smooth'})));
})();