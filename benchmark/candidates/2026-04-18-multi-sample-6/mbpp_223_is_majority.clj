(defn is_majority
  "	Write a function that takes in a sorted vector, its length (n), and an element and returns whether the element is the majority element in the given sorted vector. (The majority element is the element that occurs more than n/2 times.)"
  [arr n x]
  (let [n (or n 0)
        arr (or arr [])
        lower-bound (fn [v target]
                      (loop [lo 0
                             hi (count v)]
                        (if (< lo hi)
                          (let [mid (+ lo (quot (- hi lo) 2))]
                            (if (< (nth v mid) target)
                              (recur (inc mid) hi)
                              (recur lo mid)))
                          lo)))
        first-idx (lower-bound arr x)]
    (and (< first-idx n)
         (= (nth arr first-idx) x)
         (let [check-idx (+ first-idx (quot n 2))]
           (and (< check-idx n)
                (= (nth arr check-idx) x))))))