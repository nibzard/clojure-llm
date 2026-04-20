(defn problem-merge-with-a-function
  [f & maps]
  (reduce
    (fn [acc m]
      (reduce-kv
        (fn [r k v]
          (if (contains? r k)
            (assoc r k (f (get r k) v))
            (assoc r k v)))
        acc
        m))
    {}
    maps))