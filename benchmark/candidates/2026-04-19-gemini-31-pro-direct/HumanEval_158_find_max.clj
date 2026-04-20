(defn find_max [words]
  (first (sort-by (juxt (comp - count set) identity) words)))