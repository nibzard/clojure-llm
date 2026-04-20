(defn find_first_occurrence
  "Write a function to find the index of the first occurrence of a given number in a sorted vector."
  [A x]
  (if (empty? A)
    -1
    (loop [low 0
           high (dec (count A))
           result -1]
      (if (> low high)
        result
        (let [mid (quot (+ low high) 2)
              mid-val (nth A mid)]
          (cond
            (= mid-val x) (recur low (dec mid) mid)
            (< mid-val x) (recur (inc mid) high result)
            :else         (recur low (dec mid) result)))))))