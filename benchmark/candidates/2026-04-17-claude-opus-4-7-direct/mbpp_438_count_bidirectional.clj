(defn count_bidirectional
  "Write a function to count bidirectional vector pairs."
  [test_list]
  (if (empty? test_list)
    0
    (let [pairs (for [x test_list
                      y test_list
                      :when (not= x y)]
                  [x y])
          freq (frequencies pairs)]
      (reduce-kv (fn [acc pair count]
                   (let [[x y] pair]
                     (if (contains? freq [y x])
                       (if (= x y)
                         (+ acc count)
                         (+ acc count))
                       acc)))
                 0
                 freq))))