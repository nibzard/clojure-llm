(defn is_majority
  "	Write a function that takes in a sorted vector, its length (n), and an element and returns whether the element is the majority element in the given sorted vector. (The majority element is the element that occurs more than n/2 times.)"
  [arr n x]
  (let [n (or n 0)
        arr (or arr [])]
    (if (or (zero? n) (empty? arr))
      false
      (let [first-idx (loop [lo 0 hi (dec n)]
                        (if (> lo hi)
                          lo
                          (let [mid (+ lo (quot (- hi lo) 2))]
                            (if (< (nth arr mid) x)
                              (recur (inc mid) hi)
                              (recur lo (dec mid))))))]
        (and (< first-idx n)
             (= (nth arr first-idx) x)
             (let [check-idx (+ first-idx (quot n 2))]
               (and (< check-idx n)
                    (= (nth arr check-idx) x))))))))