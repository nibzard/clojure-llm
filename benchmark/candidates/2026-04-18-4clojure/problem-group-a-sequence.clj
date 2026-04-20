(defn problem-group-a-sequence [a b]
  (reduce (fn [m x]
            (let [k (a x)]
              (assoc m k (conj (get m k []) x))))
          {}
          b))