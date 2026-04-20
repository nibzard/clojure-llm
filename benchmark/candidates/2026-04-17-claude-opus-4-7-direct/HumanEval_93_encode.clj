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
  (let [vowels #{\a \e \i \o \u \A \E \I \O \U}
        shift-vowel (fn [c]
                      (let [base (if (Character/isUpperCase c) \A \a)]
                        (char (+ (int base) (mod (+ (- (int c) (int base)) 2) 26)))))]
    (apply str
           (map (fn [c]
                  (let [swapped (if (Character/isUpperCase c)
                                  (Character/toLowerCase c)
                                  (Character/toUpperCase c))]
                    (if (contains? vowels swapped)
                      (shift-vowel swapped)
                      swapped)))
                message))))