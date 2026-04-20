(defn find_First_Missing
  "	Write a cljthon function to find the smallest missing number from a sorted list of natural numbers."
  [array]
  (let [v (vec (or array []))
        n (count v)]
    (loop [lo 0
           hi n]
      (if (< lo hi)
        (let [mid (+ lo (quot (- hi lo) 2))]
          (if (= (nth v mid) mid)
            (recur (inc mid) hi)
            (recur lo mid)))
        lo))))