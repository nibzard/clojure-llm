(defn flatten-map
  "Return a flat map from a nested map structure by joining nested keys with dots.

  Examples:
  (flatten-map {:a {:b 1 :c {:d 2}}})
  => {\"a.b\" 1, \"a.c.d\" 2}

  (flatten-map nil)
  => {}"
  [m]
  (letfn [(step [prefix x]
            (cond
              (nil? x) {}
              (map? x) (reduce-kv
                        (fn [acc k v]
                          (merge acc (step (if (empty? prefix)
                                             (name k)
                                             (str prefix "." (name k)))
                                           v)))
                        {} x)
              :else {prefix x}))]
    (if (map? m) (step "" m) {})))