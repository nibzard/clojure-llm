(defn solve
  "Given a vector v, return a new vector where every keyword is replaced by the same keyword with its name reversed,
  and every non-keyword value is left unchanged.

  If the vector contains no keywords, reverse the vector.

  Examples:
  >>> (solve [:ab 1 :C :clojure])
  [:ba 1 :C erujolc]
  >>> (solve [1 2 3])
  [3 2 1]
  >>> (solve [:x nil true \"ok\"])
  [:x nil true \"ok\"]
  "
  [v]
  (if (some keyword? v)
    (mapv (fn [x]
            (if (keyword? x)
              (keyword (apply str (reverse (name x))))
              x))
          v)
    (vec (reverse v))))