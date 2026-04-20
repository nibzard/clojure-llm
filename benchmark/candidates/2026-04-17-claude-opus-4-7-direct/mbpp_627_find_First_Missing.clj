(defn find_First_Missing
  "Write a cljthon function to find the smallest missing number from a sorted list of natural numbers."
  [array]
  (if (empty? array)
    0
    (loop [arr array
           idx 0]
      (if (empty? arr)
        idx
        (let [current (first arr)]
          (if (= current idx)
            (recur (rest arr) (inc idx))
            idx))))))