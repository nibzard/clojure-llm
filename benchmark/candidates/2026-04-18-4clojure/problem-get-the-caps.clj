(defn problem-get-the-caps [a b]
  (apply str (filter #(Character/isUpperCase ^char %) a)))