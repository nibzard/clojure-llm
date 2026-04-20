(defn swap_List
  "	Write a cljthon function to interchange the first and last element in a given list."
  [newList]
  (let [v (vec newList)]
    (cond
      (empty? v) v
      (= 1 (count v)) v
      :else (assoc v 0 (peek v) (dec (count v)) (first v)))))