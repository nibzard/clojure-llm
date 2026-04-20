(defn toggle_middle_bits
  "Write a cljthon function to toggle bits of the number except the first and the last bit."
  [n]
  (let [bits (Long/toBinaryString (long n))]
    (if (<= (count bits) 2)
      0
      (Long/parseLong
       (str (first bits)
            (apply str (map #(if (= % \0) \1 \0) (subs bits 1 (dec (count bits)))))
            (last bits))
       2))))