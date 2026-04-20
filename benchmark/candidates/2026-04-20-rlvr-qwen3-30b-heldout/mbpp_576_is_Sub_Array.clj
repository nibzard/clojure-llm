(defn is_Sub_Array
  "	Write a cljthon function to check whether a list is sublist of another or not."
  [A B]
  (let [a (seq A)
        b (seq B)]
    (cond
      (nil? a) true
      (nil? b) false
      :else (loop [xs b]
              (if (nil? xs)
                false
                (if (= a (take (count a) xs))
                  true
                  (recur (next xs))))))))