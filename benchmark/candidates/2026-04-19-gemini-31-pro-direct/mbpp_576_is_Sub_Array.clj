(defn is_Sub_Array [A B]
  (if (empty? A)
    true
    (boolean (some #(= (seq A) %) (partition (count A) 1 B)))))