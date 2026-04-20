(defn problem-set-intersection [a b]
  (reduce (fn [acc x] (if (contains? b x) (conj acc x) acc)) #{} a))