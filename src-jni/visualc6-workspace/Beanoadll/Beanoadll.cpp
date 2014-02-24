// Beanoadll.cpp : Defines the entry point for the DLL application.
//

#include "stdafx.h"

namespace beanoacpp{

	namespace bean {
		class Person {
	
		public:
			Person(){
				cout<<endl<<"beanoacpp::bean::Person";
			}
		};


		class Address {
		private:
			string country;
			string state;
			string line1;
			string line2;


		public:
			Address(){

			}

			void setCountry(string _country){
				country = _country;
			}

			string getCountry(){
				return country;
			}

			void setState(string _state){
				state = _state;
			}

			string getState(){
				return state;
			}

			void setLine1(string _line1){
				line1 = _line1;
			}

			string getLine1(){
				return line1;
			}

			void setLine2(string _line2){
				line2 = _line2;
			}

			string getLine2(){
				return line2;
			}
		};

		class Beanoa {

		protected:
			//jni attributes
			JNIEnv *jnienv;
			char *javat;
			jmethodID constructor;
			jobject target;
			jclass  javac;

			jmethodID getMethod(jclass _jclass, char *_name, char *_ass){
				return jnienv->GetMethodID(_jclass, _name, _ass);
			}

			void sinit(jobject _target){
				target = _target;
				javac = jnienv->GetObjectClass(_target);
			}

			jobject newTarget(){
				javac = jnienv->FindClass(javat);
				constructor = jnienv->GetMethodID(javac, "<init>", "()V");

				target = jnienv->NewObject(javac, constructor);

				return target;
			}

		public:
			Beanoa(JNIEnv *_jnienv){
				jnienv = _jnienv;
			}

			jobject getTarget(){
				return target;
			}
		};

		class TelephoneType: public Beanoa {

		private:
			jmethodID getCode;

			void init(){
				javat = "net/sourceforge/beanoa/bean/TelephoneType";
			}

		public:
			TelephoneType(JNIEnv *_jnienv):Beanoa(_jnienv){
				init();
				newTarget();
			}

			TelephoneType(JNIEnv *_jnienv, jobject _target): Beanoa(_jnienv){
				target = _target;
				init();

				sinit(_target);
			}

		};

		class Telephone: public Beanoa {

		private:
			//jni attributes


			//methods' ID
			jmethodID number;
			jmethodID type;

			void init(){
				 javat = "net/sourceforge/beanoa/bean/Telephone";

				 number = NULL;
				 type = NULL;
			}

			void init(jobject _target){
				init();
				javac = jnienv->GetObjectClass(_target);
			}

		public:
			Telephone(JNIEnv *_jnienv): Beanoa(_jnienv){
				init();

				newTarget();
			}

			Telephone(JNIEnv *_jnienv, jobject _target): Beanoa(_jnienv){
				jnienv = _jnienv;
				init();

				sinit(_target);
				
			}

			jstring getNumber(){
				if(NULL== number){
					number = getMethod(javac, "getNumber", "()Ljava/lang/String;");
				}

				return (jstring)jnienv->CallObjectMethod(target, number);
			}

			jstring getType(){
				if(NULL== type){
					type = getMethod(javac, "getType", "()Lnet/sourceforge/beanoa/bean/TelephoneType;");
				}
			}

		};

		class Collection {

		private:
			JNIEnv *jnienv;

			jobject jcollection;
			jclass jcollectionclass;

			jmethodID thsize;
			jmethodID thadd;
			jmethodID thremove;
			jmethodID thtoArray;

			void init(){
				jcollectionclass = jnienv->GetObjectClass(jcollection);

				thsize = jnienv->GetMethodID(jcollectionclass, "size", "()I");
				thadd  = jnienv->GetMethodID(jcollectionclass, "add", "(Ljava/lang/Object;)Z");
				thremove = jnienv->GetMethodID(jcollectionclass, "remove", "(Ljava/lang/Object;)Z");
				thtoArray = jnienv->GetMethodID(jcollectionclass, "toArray", "()[Ljava/lang/Object;");
			}

		public:
			Collection(jobject _jcollection, JNIEnv *_jnienv){
				jcollection = _jcollection;
				jnienv = _jnienv;

				init();
			}

			jsize size(){
				return jnienv->CallIntMethod(jcollection, thsize);
			}

			jobject get(jint index){
				jobjectArray _temp = (jobjectArray)jnienv->CallObjectMethod(jcollection, thtoArray);
				return jnienv->GetObjectArrayElement(_temp, index);
			}

			void add(jobject element){
				jnienv->CallBooleanMethod(jcollection, thadd, element);
			}
		};

		class Customer {
		private:
			jobject o;
			jclass  oclass;
			JNIEnv  *env;

			jmethodID name;
			jmethodID lastName;
			jmethodID age;

			jmethodID jhome;
			jobject home;

			jmethodID jtelephone;
			jmethodID jtelephoneSize;
			jint telephoneSize;
			jobjectArray telephone;

			jmethodID getMethod(jmethodID method, const char *name, const char *ass){
				oclass = env->GetObjectClass(o);
				method = env->GetMethodID(oclass, name, ass);

				return method;
			}

		public:
			Customer(jobject _o, JNIEnv *_env){
				o = _o;
				env = _env;
				jtelephone = NULL;
				jtelephoneSize = NULL;
				telephoneSize = 0;
			}

			jstring getName(){
				cout<<endl<<"beanoacpp::bean::Customer->getName()";
				name = getMethod(name, "getName", "()Ljava/lang/String;");

				cout<<endl<<"before";
				jobject _o = env->CallObjectMethod(o, name);
				cout<<_o<<endl;

				jstring _toString = (jstring)_o;
				cout<<env->GetStringUTFChars(_toString, NULL);

				jclass _oclass = env->GetObjectClass(_o);
				jmethodID _toCharArray = env->GetMethodID(_oclass, "toCharArray", "()[C");
				jmethodID _length = env->GetMethodID(_oclass, "length", "()I");
				jint _valueOfLength = env->CallIntMethod(_o, _length);
				cout<<endl<<_valueOfLength;

				jmethodID _charAt = env->GetMethodID(_oclass, "charAt", "(I)C");
				jchar *chars = new jchar[_valueOfLength];
				for(int _index = 0; _index < _valueOfLength; _index++){
					jchar _valueOfCharAt = env->CallCharMethod(_o, _charAt, _index);
					cout<<endl<<_valueOfCharAt;
					chars[_index] = _valueOfCharAt;	
				}


				jcharArray _jcarray = env->NewCharArray(_valueOfLength);
				env->SetCharArrayRegion(_jcarray, 0, _valueOfLength, chars);

				jmethodID _constr = env->GetMethodID(_oclass, "<init>", "([C)V");
				jobject _string = env->NewObject(_oclass, _constr, _jcarray);
				cout<<(jstring)_string<<endl;
				cout<<env->GetStringUTFChars((jstring)_string, NULL);
				cout<<endl<<"after";

				return (jstring)_string;
			}

			jobjectArray getTelephone(){
				if(jtelephone == NULL){
					jtelephone = getMethod(jtelephone, "getTelephone", "()Ljava/util/Collection;");
					telephone = (jobjectArray)env->CallObjectMethod(o, jtelephone);
				}

				jclass jtelephoneClass = env->GetObjectClass(telephone);
				if(jtelephoneSize == NULL){
					jtelephoneSize = env->GetMethodID(jtelephoneClass, "size", "()I");
					telephoneSize = env->CallIntMethod(telephone, jtelephoneSize);
				}
				cout<<endl<<"Telephone's size: "<<telephoneSize;

				jmethodID _toArray = env->GetMethodID(jtelephoneClass, "toArray", "()[Ljava/lang/Object;");
				telephone = (jobjectArray)env->CallObjectMethod(telephone, _toArray);
				
				for(int _index = 0; _index < telephoneSize; _index++){
					jobject _object = env->GetObjectArrayElement(telephone, _index);

					jclass _tclass = env->GetObjectClass(_object);
					jmethodID _jmnumber = env->GetMethodID(_tclass, "getNumber", "()Ljava/lang/String;");

					jstring _number = (jstring)env->CallObjectMethod(_object, _jmnumber);
					cout<<endl<<env->GetStringUTFChars(_number, NULL);
				}

				cout<<endl<<"Native Collection test";

				jobject _jcolct = env->CallObjectMethod(o, jtelephone);
				jclass _ttype = env->FindClass("net/sourceforge/beanoa/bean/Telephone");
				Collection *_collection = new Collection(_jcolct, env);
				cout<<endl<<_collection->size();

				for(_index = 0; _index < _collection->size(); _index++){
					Telephone *_telephone = new Telephone(env, _collection->get(_index));
					cout<<endl<<_telephone->getNumber();
				}
				cout<<endl<<"add new element";
				/*
				jclass _tclass = env->FindClass("net/sourceforge/beanoa/bean/Telephone");
				jmethodID _constrct = env->GetMethodID(_tclass, "<init>", "()V");
				jobject _newt = env->NewObject(_tclass, _constrct);
				*/
				Telephone *_newt = new Telephone(env);
				_collection->add(_newt->getTarget());
				cout<<endl<<_collection->size();

				for(_index = 0; _index < _collection->size(); _index++){
					cout<<endl<<_collection->get(_index);
				}
				/*
				Collection *_collection = new Collection(this->o, env, telephoneSize, telephone);
				cout<<endl<<_collection->size();

				for(int _index2 = 0; _index2 < _collection->size(); _index2++){
					cout<<endl<<_collection->get(_index2);
				}

				cout<<endl<<"add new element";
				jclass _tclass = env->FindClass("net/sourceforge/beanoa/bean/Telephone");
				jmethodID _constrct = env->GetMethodID(_tclass, "<init>", "()V");
				jobject _newt = env->NewObject(_tclass, _constrct);

				_collection->add(_newt);
				for(_index2 = 0; _index2 < _collection->size(); _index2++){
					cout<<endl<<_collection->get(_index2);
				}
				*/
				return telephone;
			}

			jobject getTelephone(jsize index){

			}
		};
	}
}

class Person {

	private:
		int age;
		string name;

	public:
		Person(){
			age = 28;
			name = "JOSEPH";

			cout<<endl<<"Person creation.";
		}

		Person(int _age, string _name){
			age = _age;
			name = _name;

			cout<<endl<<"create "<<age<<" "<<name;
		}

		~Person(){
			cout<<endl<<"destroyer "<<age<<" "<<name;
		}

		string getName(){
			cout<<endl<<"Person->getName()";
			return name;
		}

		int getAge(){
			return age;
		}
};

class Alien {

	public:
		Alien(){
			cout<<endl<<"Alien creation.";
		}

		string getName(){
			cout<<endl<<"Alien->getName()";
			return "Wood Alien";
		}

};

class Freak {

public:
	Freak(){
		cout<<endl<<"Freak creation.";
	}

	virtual string getName(){
		cout<<endl<<"Freak->getName()";
		return "freak.i.am";
	}
};

class Customer: public Person, public Alien, public Freak {

	private:
		Customer(){
			cout<<endl<<"Customer creation ";
		}

	public:

		static Customer* getInstance(){
			return new Customer();
		}

		string getName(){
			cout<<endl<<"Customer->getName()";
			return "JOSEPH, fabio";
		}
};

class Address {
    private:
      jclass bclass;
      jobject bjobject;
      JNIEnv *jnienv;
      jlong id;
      jstring state;
      jstring country;	
      jstring line1;
      jstring line2;
    public:
      Address (jobject __bjobject, JNIEnv *__jnienv){
        bjobject = __bjobject;
        jnienv = __jnienv;
      }
      jlong getId() {
        return id;
      }
      jstring getState() {
        return state;
      }
      jstring getCountry() {
        return country;
      }
      jstring getLine1() {
        return line1;
      }
      jstring getLine2() {
        return line2;
      }
      void setState(jstring __state){
        state = __state;
      }
      void setId(jlong __id){
        id = __id;
      }
      void setCountry(jstring __country){
        country = __country;
      }
      void setLine1(jstring __line1){
        line1 = __line1;
      }
      void setLine2(jstring __line2){
        line2 = __line2;
      }
  };

BOOL APIENTRY DllMain( HANDLE hModule, 
                       DWORD  ul_reason_for_call, 
                       LPVOID lpReserved
					 )
{
    return TRUE;
}

#ifdef __cplusplus
extern "C" {
#endif

JNIEXPORT jint JNICALL Java_net_sourceforge_beanoa_jni_BeanoaJNI_write
(JNIEnv *env, jobject obj, jint value){

	
	printf("short time não!");
	return 666 * value;
}

JNIEXPORT jint JNICALL Java_net_sourceforge_beanoa_jni_BeanoaJNI_doInjection
(JNIEnv *env, jobject caller, jobject customer){

	jclass cls = env->GetObjectClass(customer);
	jmethodID mid = env->GetMethodID(cls, "setAge", "(I)V");
	if(NULL== mid){
		return 666;
	}

	jvalue *args = new jvalue();
	args->i = 8;

	//calling the setter
	env->CallVoidMethodA(customer, mid, args);

	mid = env->GetMethodID(cls, "getHome", "()Lnet/sourceforge/beanoa/bean/Address;");
	if(NULL== mid){
		return 666;
	}

	//calling the getter
	jobject home = env->CallObjectMethod(customer, mid);
	
	cls = env->GetObjectClass(home);
	mid = env->GetMethodID(cls, "setCountry", "(Ljava/lang/String;)V");

	jstring str = env->NewStringUTF("SOUTH AFRICA");
	if(NULL== str){
		return 667;
	}

	cls = env->FindClass("java/lang/String");
	jobjectArray args2 = env->NewObjectArray(1, cls, str);
	if(NULL== args2){
		return 666;
	}

	
	//calling the setter on Address object
	args = new jvalue();
	args->l = str;

	env->CallVoidMethodA(home, mid, args);

	cout<<endl<<"endtime"<<endl;

	Person *person = new Person(32, "MARLEY");
	
	Customer *__customer = Customer::getInstance();
	cout<<endl<<__customer->getName();

	Freak *_freak = __customer;
	cout<<endl<<"FREAK name´s: "<<_freak->getName();

	beanoacpp::bean::Person *_bpeson = new beanoacpp::bean::Person();

	beanoacpp::bean::Customer *_bcustomer = new beanoacpp::bean::Customer(customer, env);
	cout<<endl<<env->GetStringUTFChars( _bcustomer->getName(), NULL );

	cout<<endl<<"Collection";
	_bcustomer->getTelephone();

	cout<<endl<<"the end!";
	return 0;
}

#ifdef __cplusplus
}
#endif

