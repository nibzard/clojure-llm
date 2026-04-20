(defn max-Product
  "Write a cljthon function to find a pair with highest product from a given vector of integers."
  [arr]
  (if (< (count arr) 2)
    nil
    (let [sorted-arr (sort arr)
          n (count sorted-arr)
          ;; The two smallest numbers (could be negative)
          min1 (nth sorted-arr 0)
          min2 (nth sorted-arr 1)
          ;; The two largest numbers
          max1 (nth sorted-arr (dec n))
          max2 (nth sorted-arr (- n 2))]
      (max (* min1 min2)
           (* max1 max2)))))