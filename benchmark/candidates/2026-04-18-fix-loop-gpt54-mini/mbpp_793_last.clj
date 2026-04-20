(defn last
  "	Write a cljthon function to find the last position of an element in a sorted vector."
  [arr x]
  (loop [lo 0
         hi (dec (count arr))
         ans -1]
    (if (> lo hi)
      ans
      (let [mid (+ lo (quot (- hi lo) 2))
            v (nth arr mid)]
        (cond
          (< v x) (recur (inc mid) hi ans)
          (> v x) (recur lo (dec mid) ans)
          :else   (recur (inc mid) hi mid))))))