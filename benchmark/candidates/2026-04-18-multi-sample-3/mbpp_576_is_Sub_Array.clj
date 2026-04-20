(defn is_Sub_Array
  "	Write a cljthon function to check whether a list is sublist of another or not."
  [A B]
  (let [a (seq A)
        b (seq B)]
    (cond
      (nil? b) true
      (nil? a) false
      :else
      (boolean
       (some #(= b %)
             (take-while seq (iterate next a)))))))