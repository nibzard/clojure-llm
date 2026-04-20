(defn zip-map
  "Return a map built from two collections by pairing keys with values.

  If either collection is shorter, stop at the shortest one.
  If keys contains duplicates, later values overwrite earlier ones.

  Examples:
  (zip-map [:a :b :c] [1 2 3])
  ;;=> {:a 1, :b 2, :c 3}

  (zip-map [:a :b] [1 2 3])
  ;;=> {:a 1, :b 2}

  (zip-map [:a :a :b] [1 2 3])
  ;;=> {:a 2, :b 3}"
  [keys values]
  (into {} (map vector keys values)))