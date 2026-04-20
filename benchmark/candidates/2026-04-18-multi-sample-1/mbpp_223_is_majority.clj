(defn is_majority
  "	Write a function that takes in a sorted vector, its length (n), and an element and returns whether the element is the majority element in the given sorted vector. (The majority element is the element that occurs more than n/2 times.)"
  [arr n x]
  (let [n (or n 0)
        arr (or arr [])
        first-index (loop [lo 0
                           hi (dec n)]
                      (if (> lo hi)
                        -1
                        (let [mid (+ lo (quot (- hi lo) 2))
                              v (nth arr mid)]
                          (cond
                            (< v x) (recur (inc mid) hi)
                            (> v x) (recur lo (dec mid))
                            :else (if (or (zero? mid) (not= (nth arr (dec mid)) x))
                                    mid
                                    (recur lo (dec mid)))))))]
    (and (not= first-index -1)
         (< (+ first-index (quot n 2)) n)
         (= (nth arr (+ first-index (quot n 2))) x))))