(defn zipvecs->map
  "Return a map from a vector of keys and a vector of values.

  If the vectors are different lengths, stop at the shortest one.
  If either input is nil, treat it as an empty vector.

  Examples:
  (zipvecs->map [:a :b :c] [1 2 3])
  ;;=> {:a 1, :b 2, :c 3}

  (zipvecs->map [:a :b] [1])
  ;;=> {:a 1}

  (zipvecs->map nil [1 2])
  ;;=> {}"
  [ks vs])