(defn last
  "	Write a cljthon function to find the last position of an element in a sorted vector."
  [arr x]
  (let [n (count arr)]
    (loop [lo 0
           hi (dec n)
           result -1]
      (if (> lo hi)
        result
        (let [mid (+ lo (quot (- hi lo) 2))
              v   (nth arr mid)]
          (cond
            (= v x) (recur (inc mid) hi mid)
            (< v x) (recur (inc mid) hi result)
            :else   (recur lo (dec mid) result)))))))