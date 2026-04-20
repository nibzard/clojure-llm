(defn encode
  [message]
  (let [vowels #{\a \e \i \o \u \A \E \I \O \U}
        transform (fn [c]
                    (let [shifted (if (vowels c)
                                    (char (+ 2 (int c)))
                                    c)]
                      (cond
                        (Character/isUpperCase shifted) (Character/toLowerCase shifted)
                        (Character/isLowerCase shifted) (Character/toUpperCase shifted)
                        :else shifted)))]
    (apply str (map transform message))))