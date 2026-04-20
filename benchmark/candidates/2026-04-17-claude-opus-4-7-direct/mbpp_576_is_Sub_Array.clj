(defn is_Sub_Array
  "Check whether list A is a sublist of list B."
  [A B]
  (cond
    (nil? A) true
    (nil? B) false
    (empty? A) true
    (empty? B) false
    :else (some #(when (= % A) true)
                (partition (count A) 1 B))))