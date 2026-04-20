(defn cycpattern_check
  "You are given 2 words. You need to return true if the second word or any of its rotations is a substring in the first word
  >>> (cycpattern_check "abcd" "abd")
  false
  >>> (cycpattern_check "hello" "ell")
  true
  >>> (cycpattern_check "whassup" "psus")
  false
  >>> (cycpattern_check "abab" "baa")
  true
  >>> (cycpattern_check "efef" "eeff")
  false
  >>> (cycpattern_check "himenss" "simen")
  true"
  [a b]
  (if (or (nil? a) (nil? b) (empty? b))
    false
    (let [b-len (count b)
          rotations (map #(subs (str b b) % (+ % b-len)) (range b-len))]
      (some #(clojure.string/includes? a %) rotations))))