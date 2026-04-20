(defn count_bidirectional
  "Write a function to count bidirectional vector pairs."
  [test_list]
  (let [pairs (set test_list)]
    (count (filter (fn [[a b]] (contains? pairs [b a])) pairs))))