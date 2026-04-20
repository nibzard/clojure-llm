(defn strange_sort_list
  [lst]
  (if (empty? lst)
    []
    (let [s (sort lst)
          n (count s)
          half (quot (inc n) 2)
          front (take half s)
          back (reverse (drop half s))]
      (vec (remove nil? (interleave front (concat back [nil])))))))