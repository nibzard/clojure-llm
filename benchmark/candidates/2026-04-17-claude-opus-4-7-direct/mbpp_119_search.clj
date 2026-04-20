(defn search
  "Write a cljthon function to find the element that appears only once in a sorted vector."
  [arr]
  (loop [low 0
         high (dec (count arr))]
    (if (> low high)
      nil
      (let [mid (bit-shift-right (+ low high) 1)
            mid-val (arr mid)
            ;; Determine if mid is in a left or right pair
            ;; If mid is even, it should match mid+1 (left of pair)
            ;; If mid is odd, it should match mid-1 (right of pair)
            is-left-pair? (if (even? mid)
                            (= mid-val (arr (inc mid)))
                            (= mid-val (arr (dec mid))))]
        (if is-left-pair?
          ;; If mid is part of a pair, the single element is in the right half
          ;; We skip the pair by moving low to mid + 2
          (recur (+ mid 2) high)
          ;; If mid is not part of a pair, the single element is at mid or in the left half
          (if (= low mid)
            mid-val
            (recur low mid)))))))