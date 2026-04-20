(defn encrypt
  "Create a function encrypt that takes a string as an argument and
  returns a string encrypted with the alphabet being rotated. 
  The alphabet should be rotated in a manner such that the letters 
  shift down by two multiplied to two places (i.e., 4 places)."
  [s]
  (let [shift 4
        shift-char (fn [c]
                     (cond
                       (<= (int \a) (int c) (int \z))
                       (char (+ (int \a) (mod (+ (- (int c) (int \a)) shift) 26)))
                       
                       (<= (int \A) (int c) (int \Z))
                       (char (+ (int \A) (mod (+ (- (int c) (int \A)) shift) 26)))
                       
                       :else c))]
    (apply str (map shift-char s))))