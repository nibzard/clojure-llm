(defn count_reverse_pairs
  "Write a function to count the pairs of reverse strings in the given string list."
  [test_list]
  (if (empty? test_list)
    0
    (let [freq (frequencies test_list)]
      (reduce (fn [acc [k v]]
                (let [rev-k (apply str (reverse k))]
                  (if (contains? freq rev-k)
                    (let [v-rev (get freq rev-k)]
                      (cond
                        (= k rev-k) (+ acc (quot v 2))
                        (compare k rev-k) (+ acc (* v v-rev))
                        :else acc))
                    acc)))
              0
              freq))))