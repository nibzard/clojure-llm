(defn find_combinations
  "Write a function to find the combinations of sums with vectors in the given vector list."
  [test_list]
  (if (empty? test_list)
    []
    (let [vectors (vec (filter vector? test_list))
          n (count vectors)]
      (if (zero? n)
        []
        (loop [result []
               i 0]
          (if (>= i n)
            result
            (let [v1 (nth vectors i)
                  len1 (count v1)]
              (if (zero? len1)
                (recur result (inc i))
                (recur (into result 
                             (for [j (range (inc i) n)
                                   :let [v2 (nth vectors j)]
                                   :when (seq v2)]
                               (vec (map + v1 v2))))
                       (inc i))))))))))