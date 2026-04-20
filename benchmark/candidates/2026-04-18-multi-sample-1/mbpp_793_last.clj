(defn last
  "	Write a cljthon function to find the last position of an element in a sorted vector."
  [arr x]
  (when (seq arr)
    (loop [lo 0
           hi (dec (count arr))
           res nil]
      (if (> lo hi)
        res
        (let [mid (+ lo (quot (- hi lo) 2))
              v   (nth arr mid)]
          (cond
            (= v x) (recur (inc mid) hi mid)
            (< v x) (recur (inc mid) hi res)
            :else   (recur lo (dec mid) res)))))))