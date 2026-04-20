(defn find_sum
  "Write a cljthon function to find the sum of non-repeated elements in a given list."
  [arr]
  (let [freqs (frequencies arr)]
    (reduce + 0
            (for [[x n] freqs :when (= n 1)]
              x))))