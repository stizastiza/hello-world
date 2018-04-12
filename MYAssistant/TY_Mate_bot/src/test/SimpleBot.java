package test;

import org.telegram.telegrambots.exceptions.TelegramApiException;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.TelegramBotsApi;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.methods.send.SendPhoto;
import org.telegram.telegrambots.api.objects.Message;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
 
public class SimpleBot extends TelegramLongPollingBot {
 
	public static void main(String[] args) {
		ApiContextInitializer.init();
		TelegramBotsApi telegramBotsApi = new TelegramBotsApi();
		try {
			telegramBotsApi.registerBot(new SimpleBot());
		} catch (TelegramApiException e) {
			e.printStackTrace();
		}
	}
 
	@Override
	public String getBotUsername() {
		return "TY_Assistant_Bot";
	}
 
	@Override
	public String getBotToken() {
		return "363999800:AAGHUWB6F6Y0BAbn8D6MYco_9ORMzC81bWU";
	}
 
	
	@Override
	public void onUpdateReceived(Update update) {
		Message message = update.getMessage();
		if (message != null && message.hasText()) {
			//sendMsgNoNo("@kostjaigin", "I am in use");
			if (message.getText().equals("/start")) {
				sendMsgNoRep(message, "С днем рождения, Таня:3 В двух словах: этот бот должен избавить тебя от страданий по несданным домашкам, не дав тебе при этом сильно расслабиться!");
				this.waitin(2000);
				sendMsgNoRep(message, "Он будет открывать тебе доступ к решенным мною в прошлом году домашкам по introprog за некоторое (предположительно, небольшое) время до официальной сдачи. Над временем мы еще поработаем.");
				this.waitin(5000);
				sendMsgNoRep(message, "Предлагаю сразу попробовать одну из немногих пока его функций и ввести /command1");
				this.waitin(2000);
				sendMsgNoRep(message, "Хочу тут сразу предупредить - я не знаю, насколько задания в этом семестре отличаются от наших, но концептуально они должны быть эквивалентны");
				
			}
			
			else if (message.getText().equals("/command1")) {
				sendMsgNoRep(message, "/Blatt1" + "\n" + "/Blatt2" + "\n" + "/Blatt3" + "\n" + "/Blatt4" + "\n" + "/Blatt5" + "\n" + "/Blatt6" + "\n" + "/Blatt7" + "\n" + "/Blatt8" + "\n" + "/Blatt9" + "\n" + "/Blatt10" + "\n" + "/Blatt11");
				/* Old Version 1:
				//this.waitin(2000);
				//sendMsgNoRep(message, "Есть еще одна спрятанная функция в другой функции :3 мы ж информатики");
				//this.waitin(3000);
				//sendMsgNoRep(message, "Введи без кавычек 'I am 21 now'");
				 * 
				 */
			}
			// 1. Blatt:
			else if (message.getText().equals("/Blatt1") && message.getDate() >= 1511136000) {
				sendMsgNoRep(message, "https://yadi.sk/d/-SqMWUoY3PEag6");
			}
				else if (message.getText().equals("/Blatt1") && message.getDate() < 1511136000) {
					sendMsgNoRep(message, "Access to solution denied. You get this first at 20.11.2017 at 00:00:00");
					this.waitin(2000);
					sendMsgNoRep(message, "Зато можно посмотреть видяху с нашим любимым мужичком:D ");
					this.waitin(2000);
					sendMsgNoRep(message, "https://www.youtube.com/watch?v=kU9M51eKSX8");
					this.waitin(3000);
					sendMsgNoRep(message, "Но я не зверь, и если ты хочешь заранее ознакомиться с грядущим заданием и начать решать его раньше остальных:");
					this.waitin(3000);
					sendMsgNoRep(message, "https://yadi.sk/d/28PPb2zc3PGgey");
				}
			// 2. Blatt:
			else if (message.getText().equals("/GetDebugging")) {
				sendMsgNoRep(message, "https://www.youtube.com/watch?v=EQG-ejHMbYM&index=9&list=PLa5fRh6uTXwOyJ6_VVy8ypNf6AyFRDCXp");
				this.waitin(2000);
				sendMsg(message, "https://www.youtube.com/watch?v=EQG-ejHMbYM&index=9&list=PLa5fRh6uTXwOyJ6_VVy8ypNf6AyFRDCXp");
			}
			// 3. Blatt:
			else if (message.getText().equals("/Blatt3") || message.getText().equals("/Blatt2")) /*&& message.getDate() >= 1511694000*/ {
				sendPhotoNoRep("https://pp.userapi.com/c840728/v840728129/27479/x6u1Y8gzyAg.jpg", message);
				this.waitin(5000);
				sendMsgNoRep(message, "Наш Blatt2 был на тему Debugging. Твой - Laufzeit. Поэтому он сразу скинет домашку и баллы по лауфцайт. Если нужен блатт с Debugging: /GetDebugging");
				this.waitin(2000);
				sendMsgNoRep(message, "https://yadi.sk/d/Q8-q-7Dz3PEah2");
			}
			// Besondere Funktion:
				else if (message.getText().equals("I am 21 now")) {
					sendMsgNoRep(message, "Принято. Между сообщениями будет небольшая пауза, чтобы ты успевала их читать");
					this.waitin(3000);
					sendMsgNoRep(message, "Как-то раз мы с тобой шли по улице и я спросил, что бы ты хотела получить на день Рождения");
					this.waitin(4000);
					sendMsgNoRep(message, "Ты в шутку сказала, что хотела бы такую книжечку, типа Anleitung");
					this.waitin(5000);
					sendMsgNoRep(message, "А-ля что мне делать со своей жизнью, карманное руководство с пошаговым описанием");
					this.waitin(5000);
					sendMsgNoRep(message, "Это действительно классно, и я подготовил такой Anleitung");
					this.waitin(5000);
					sendMsgNoRep(message, "Чтобы получить его, набери этому боту без кавычек: 'What should i do with my life?'");
				}
			
			else if (message.getText().equals("What should i do with my life?")) {
				sendMsgNoRep(message, "Я бы разложил все по шагам, как ты и хотела");
				this.waitin(5000);
				sendMsgNoRep(message, "Только вот это немного невозможно");
				this.waitin(5000);
				sendMsgNoRep(message, "Если бы и было возможно, то настоящее счастье было бы недостижимо");
				this.waitin(5000);
				sendMsgNoRep(message, "Потому что в нашей беспорядочности, хаотичности, временами безответственности");
				this.waitin(5000);
				sendMsgNoRep(message, "мы находим такие моменты, которые невозможно найти в запланированном пошаговом расписании");
				this.waitin(5000);
				sendMsgNoRep(message, "Ты, Татьяна Яковенко, сейчас проходишь сложный путь начала обучения в чужой стране");
				this.waitin(5000);
				sendMsgNoRep(message, "есть одно но! Ты проходишь его в чужой стране не одна, а со своими людьми.");
				this.waitin(5000);
				sendMsgNoRep(message, "Поэтому один единственный совет, который все-таки даст этот воображаемый Anleitung от моего имени");
				this.waitin(5000);
				sendMsgNoRep(message, "Делай просто все необходимое, чтобы дальше оставаться тем человеком, который занимается тем, что ему интересно, рядом с теми, кто ему важен");
				this.waitin(5000);
				sendMsgNoRep(message, "Воооот. Все остальные инструкции приложатся. Ну и они есть еще в интернете, гугл еще никто не отменял");
				this.waitin(5000);
				sendMsgNoRep(message, "Так что прими мои поздравления! В моих глазах, сейчас, на данный момент, ты именно такой человек!");
				this.waitin(5000);
				sendMsgNoRep(message, "Так и делать ничего не НАДА :3 ");
				}
			else if (message.getText().equals("i totally fucked up with my life") || message.getText().equals("I totally fucked up with my life")) {
				sendPhotoNoRep("https://pp.userapi.com/c841621/v841621238/3296b/gV9qAgg0Vok.jpg", message);
				this.waitin(4000);
				sendPhotoNoRep("https://pp.userapi.com/c836729/v836729838/2d305/ZW3FAZ5ZwmU.jpg", message);
			}
			else if (message.getText().equals("/Blatt4")) {
				sendMsgNoRep(message, "₽₽₽₽₽");
				this.waitin(4000);
				sendPhotoNoRep("https://lh5.googleusercontent.com/PbUbeBc3dfS2ne0KrXJD6_4JFI6T_Pl4me1bwHF1r3vIrd664XlRAIQDW_dGfIl9TMAgDLhxEAaj5IsSRi6FhSmqRws5zmYiTstxnRLAmtY9wGswZzBoAan227yLceM4cVnYl7GE", message);
				this.waitin(3000);
				sendMsgNoRep(message, "https://yadi.sk/d/nxNBAV4K3PEahC");
			}
			else if (message.getText().equals("/Blatt5") && message.getDate() >= 1513096520) {
				sendMsgNoRep(message, "₽₽₽₽₽");
				this.waitin(3000);
				sendMsgNoRep(message, "https://yadi.sk/d/wtz4x2fe3PEahY");
			}
			else if (message.getText().equals("/Blatt5") && message.getDate() < 1513096520) {
				this.waitin(1000);
				sendMsgNoRep(message, "Пока нельзя:(");
			}
			else if (message.getText().equals("/Blatt7")) {
				this.waitin(1000);
				sendMsgNoRep(message, "Ausnahmsweise, dieses Blatt kriegst du ohne Zeitbegrenzung");
				this.waitin(2000);
				sendMsgNoRep(message, "https://yadi.sk/d/TSzBVdB93PEahx");
				this.waitin(10000);
				sendMsgNoRep(message, "я тебя люблю");
				this.waitin(5000);
				sendMsgNoRep(message, "Я тут, кстати, еще решил написать еще одну функцию в два часа ночи");
				this.waitin(5000);
				sendMsgNoRep(message, "Как ты помнишь, мы создали git папку у тебя на компе, в которой лежит испфайл бота");
				this.waitin(2000);
				sendMsgNoRep(message, "Хочу напомнить, что этот файл нужно обновлять. Чтобы получить разовую инструкцию, введи: update -h");
			}
			else if (message.getText().equals("/Blatt8")) {
				sendMsgNoRep(message, "https://yadi.sk/d/lDefaCdI3PEaiP");
				this.waitin(2000);
				sendMsgNoRep(message, "Обращай внимание, что решения находятся в папке Abgabe!");
			}
			else if (message.getText().equals("/Exit") || message.getText().equals("/command3")) {
				sendMsgNoRep(message, "Закрываю поток на сервере");
				System.exit(1);
			}
			else if (message.getText().equals("update -h")) {
				sendMsgNoRep(message, "(1) На всякий случай дистанционно вырубаем бота на сервере с помощью /Exit");
				sendMsgNoRep(message, "(2) Заходим в git папку с исполнительным файлом через командную строку");
				sendMsgNoRep(message, "(3) git pull");
				sendMsgNoRep(message, "(4) Дважды клик на RunMYAssistant.jar");
				sendMsgNoRep(message, "(5) Profit");
			}
			else if (message.getText().equals("/Blatt9")) {
				sendMsgNoRep(message, "Quick Sort:");
				this.waitin(3000);
				sendMsgNoRep(message, "https://yadi.sk/d/jTpjFLVr3PEagE");
			}
			else if (message.getText().equals("/Blatt10") && message.getDate() >= 1518480000) {
				sendMsgNoRep(message, "AVL Trees:");
				this.waitin(3000);
				sendMsgNoRep(message, "https://yadi.sk/d/vaWd99bM3PEagQ");
			}
			else if (message.getText().equals("/Blatt10") && message.getDate() <  1518480000) {
				sendMsgNoRep(message, "Я откроюсь чу-чуть позже:)");
			}
			
			// Alle Anderen Blaetter zum Nacharbeiten:
			else if (this.inWork(message, message.getText(), 11)) {
				sendMsgNoRep(message, "Access Denied. Ну я типа написал тут готовую структуру, но добавлять ссылочки занимает время, и я буду добавлять их с каждым днем по одной, так что по срокам успеем!");
				this.waitin(2000);
				sendMsgNoRep(message, "Если есть время, можешь изучить будущие задания по листкам:");
				this.waitin(2000);
				sendMsgNoRep(message, "https://yadi.sk/d/6XoBSAao3PGhxW");
			}

			// NEW YEAR Einleitung:
			// ₽1:
			else if (message.getText().equals("1523791")) {
				sendMsgNoRep(message, "Привет:) Это уже давно не оригинально, но надеюсь, ты будешь рада услышать мои слова.");
				this.waitin(5000);
				sendMsgNoRep(message, "Идея зародилась у меня давно, но из-за поездки в Москву я не успевал с ней справиться");
				this.waitin(5000);
				sendMsgNoRep(message, "В Москве этим заняться особо не получилось - но тем-то лучше. Мы сделаем это вместе.");
				this.waitin(5000);
				sendMsgNoRep(message, "В первую очередь");
				this.waitin(5000);
				sendMsgNoRep(message, "Таня");
				this.waitin(5000);
				sendMsgNoRep(message, "я тебя люблю");
				this.waitin(5000);
				sendMsgNoRep(message, "Я говорю это часто, но слова от этого не становятся менее значимыми, особенно сейчас");
				this.waitin(5000);
				sendMsgNoRep(message, "В новом году я желаю провести столько времени с тобой, сколько мы сможем провести");
				this.waitin(5000);
				sendMsgNoRep(message, "В новом году я хотел бы сделать все, чтобы мы могли сделать все запланированное: вместе учиться, работать над интересными вещами, проводить лучшие совместные дни");
				this.waitin(5000);
				sendMsgNoRep(message, "Первый номер - это 1523791 - случайный номер из головы..");
				this.waitin(5000);
				sendMsgNoRep(message, "И еще одна запись в блокнот наших дел");
				this.waitin(5000);
				sendMsgNoRep(message, "Это была мысль:) Соединить этого бота к сегодняшнему дню с твоей Rapsberry");
				this.waitin(5000);
				sendMsgNoRep(message, "Получив постоянный сервер на Rapsberry, да еще и таким образом, получив управление через бота всеми возможными приборами, которые ты с ней соберешь");
				this.waitin(5000);
				sendMsgNoRep(message, "Но один я не справился. И не справлюсь. Я хочу, чтобы ты была рядом и делала это со мной");
				this.waitin(5000);
				sendMsgNoRep(message, "И не просто сервер, а сервер, управление которым будет скрываться в этом боте. Представь себе, как далеко это может зайти..");
				this.waitin(5000);
				sendMsgNoRep(message, "₽₽₽₽₽₽₽₽₽₽₽₽₽₽₽₽₽₽₽₽₽₽₽₽₽₽₽₽₽₽₽₽₽₽");
				this.waitin(5000);
				sendMsgNoRep(message, "https://ru.wikihow.com/%D1%81%D0%B4%D0%B5%D0%BB%D0%B0%D1%82%D1%8C-%D0%B2%D0%B5%D0%B1-%D1%81%D0%B5%D1%80%D0%B2%D0%B5%D1%80-%D0%BD%D0%B0-%D0%B1%D0%B0%D0%B7%D0%B5-Raspberry-Pi");
				this.waitin(5000);
				sendMsgNoRep(message, "₽₽₽₽₽₽₽₽₽₽₽₽₽₽₽₽₽₽₽₽₽₽₽₽₽₽₽₽₽₽₽₽₽₽");
			}
			// ₽2:
			else if (message.getText().equals("1889137")) {
				sendMsgNoRep(message, "");
				
			}
			// ₽3:
			else if (message.getText().equals("132958")) {
				sendMsgNoRep(message, "");
				
			}
			
			
			
		}
	}
 
	// Send messages with reply:
	private void sendMsg(Message message, String text) {
		SendMessage sendMessage = new SendMessage();
		sendMessage.enableMarkdown(true);
		sendMessage.setChatId(message.getChatId().toString());
		sendMessage.setReplyToMessageId(message.getMessageId());
		sendMessage.setText(text);
		try {
			sendMessage(sendMessage);
		} catch (TelegramApiException e) {
			e.printStackTrace();
		}
	}
	
	// Sending messages without reply-function:
	private void sendMsgNoRep(Message message, String text) {
		SendMessage sendMessage = new SendMessage();
		sendMessage.enableMarkdown(true);
		sendMessage.setChatId(message.getChatId().toString());
		sendMessage.setText(text);
		try {
			sendMessage(sendMessage);
		}	catch (TelegramApiException e) {
			e.printStackTrace();
		}
	}
	
	// Sending photos without reply-function:
	private void sendPhotoNoRep(String http, Message message) {
		SendPhoto sendPhoto = new SendPhoto();
		sendPhoto.setChatId(message.getChatId().toString());
		sendPhoto.setPhoto(http);
		try {
			sendPhoto(sendPhoto);
		} catch (TelegramApiException e) {
			e.printStackTrace();
		}
	}
	private void sendPhotoNoRepLocal(String address, Message message) {
		SendPhoto sendPhoto = new SendPhoto();
		sendPhoto.setChatId(message.getChatId().toString());
		sendPhoto.setPhoto(address);
		try {
			sendPhoto(sendPhoto);
		} catch (TelegramApiException e) {
			e.printStackTrace();
		}
	}
	
	
	//define other classes and methods here:
	
	// Tasks in order to be done:
	private boolean inWork(Message message, String text, int k) {
		boolean tmp = false;
		for (int i = k; i<=11; i++) {
			if (message.getText().equals("/Blatt"+i)) {
				tmp = true;
			}
		}
		return tmp;
	}
	// Wait-Function:
	private void waitin(int t) {
		try        
		{
		    Thread.sleep(t);
		} 
		catch(InterruptedException ex) 
		{
		    Thread.currentThread().interrupt();
		}
	}
 
}