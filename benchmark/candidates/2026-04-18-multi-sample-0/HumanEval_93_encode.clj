(defn encode
  "Write a function that takes a message, and encodes in such a 
  way that it swaps case of all letters, replaces all vowels in 
  the message with the letter that appears 2 places ahead of that 
  vowel in the english alphabet. 
  Assume only letters. 
  Examples:
  >>> (encode \"test\")
  \"TGST\"
  >>> (encode \"This is a message\")
  \"tHKS KS C MGSSCGG\""
  [message]
  (let [vowel-map {\a \c, \e \g, \i \k, \o \q, \u \w
                   \A \C, \E \G, \I \K, \O \Q, \U \W}
        swap-case (fn [ch]
                    (cond
                      (Character/isUpperCase ch) (Character/toLowerCase ch)
                      (Character/isLowerCase ch) (Character/toUpperCase ch)
                      :else ch))]
    (apply str
           (map (fn [ch]
                  (let [swapped (swap-case ch)]
                    (get vowel-map swapped swapped)))
                (or message "")))))