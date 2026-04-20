(defn pancake_sort
  "Write a function to sort a list of elements using pancake sort."
  [nums]
  (if (<= (count nums) 1)
    (seq nums)
    (let [max-val (apply max nums)
          max-idx (first (keep-indexed #(when (= %2 max-val) %1) nums))
          n (count nums)]
      (if (= max-idx (dec n))
        (concat (pancake_sort (take (dec n) nums)) [max-val])
        (let [flipped-once (concat (reverse (take (inc max-idx) nums))
                                   (drop (inc max-idx) nums))
              flipped-twice (reverse flipped-once)]
          (concat (pancake_sort (take (dec n) flipped-twice))
                  [max-val]))))))