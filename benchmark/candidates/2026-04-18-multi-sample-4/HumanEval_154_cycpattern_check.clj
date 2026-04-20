(defn cycpattern_check
  "You are given 2 words. You need to return true if the second word or any of its rotations is a substring in the first word
  >>> (cycpattern_check \"abcd\" \"abd\")
  false
  >>> (cycpattern_check \"hello\" \"ell\")
  true
  >>> (cycpattern_check \"whassup\" \"psus\")
  false
  >>> (cycpattern_check \"abab\" \"baa\")
  true
  >>> (cycpattern_check \"efef\" \"eeff\")
  false
  >>> (cycpattern_check \"himenss\" \"simen\")
  true"
  [a b]
  (let [a (or a "")
        b (or b "")]
    (if (empty? b)
      true
      (and (<= (count b) (count a))
           (let [doubled (str b b)]
             (boolean
              (some #(clojure.string/includes? a %)
                    (map (fn [i] (subs doubled i (+ i (count b))))
                         (range (count b))))))))))