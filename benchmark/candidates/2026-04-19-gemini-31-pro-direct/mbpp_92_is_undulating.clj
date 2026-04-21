(defn is_undulating [n]
  (let [s (str n)]
    (if (< (count s) 3)
      false
      (->> s
           (partition 3 1)
           (every? (fn [[x y z]] (and (= x z) (not= x y))))))))