(defn check_if_last_char_is_a_letter
  [txt]
  (let [s (or txt "")
        last-ch (last s)]
    (boolean
      (and last-ch
           (Character/isLetter ^char last-ch)
           (or (empty? s)
               (not= \space last-ch)
               (let [idx (.lastIndexOf s " ")]
                 (= (inc idx) (dec (count s)))))))))